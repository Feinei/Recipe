package com.example.recipe.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.recipe.utils.Resource
import com.example.recipe.utils.Status
import com.example.recipe.data.api.FoodRemoteDataSource
import com.example.recipe.data.db.FoodDao
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodRepository @Inject constructor(
    private val dao: FoodDao,
    private val remoteSource: FoodRemoteDataSource
) {

    fun getFood(name: String) = resultLiveData(
        name,
        databaseQuery = { dao.getFoodByName(it) },
        networkCall = { remoteSource.fetchData(it) },
        saveCallResult = { dao.insertAll(it.results) })


    private fun <T, A> resultLiveData(
        foodName: String,
        databaseQuery: (String) -> LiveData<T>,
        networkCall: suspend (String) -> Resource<A>,
        saveCallResult: suspend (A) -> Unit
    ): LiveData<Resource<T>> =
        liveData(Dispatchers.IO) {

            emit(Resource.loading<T>(null))
            val source = databaseQuery.invoke(foodName).map { Resource.success(it) }
            emitSource(source)

            val responseStatus = networkCall.invoke(foodName)
            if (responseStatus.status == Status.SUCCESS) {
                saveCallResult(responseStatus.data!!)
            } else if (responseStatus.status == Status.ERROR) {
                emit(Resource.error<T>(responseStatus.message!!, null))
                emitSource(source)
            }
        }
}
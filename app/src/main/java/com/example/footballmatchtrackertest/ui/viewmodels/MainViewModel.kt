package com.example.footballmatchtrackertest.ui.viewmodels

import androidx.lifecycle.*
import androidx.room.Database
import androidx.room.Query
import com.example.footballmatchtrackertest.data.repository.Repository
import com.example.footballmatchtrackertest.data.model.MatchesInfo
import com.example.footballmatchtrackertest.db.MatchesDatabase
import com.example.footballmatchtrackertest.db.model.MatchesInfoEntity
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*

class MainViewModel(private val db: MatchesDatabase) : ViewModel() {
    val dao = db.getDao()

    var repo = Repository()
    val allMatches: MutableLiveData<Response<MatchesInfo>> = MutableLiveData()
    var allMatchesFromDB: LiveData<List<MatchesInfoEntity>> = dao.getAllMatches().asLiveData()

    var myCalendar : MutableLiveData<Calendar> = MutableLiveData()

    init{
        myCalendar.value = Calendar.getInstance();
    }
    fun getAllMatchesInfo(date: String) {
        viewModelScope.launch {
            allMatches.value = repo.getAllMatchesInfo(date)
        }
    }

    fun saveMatchInfo(matchesInfoEntity: MatchesInfoEntity) {
        viewModelScope.launch {
            dao.insertRequest(matchesInfoEntity)
        }
    }

    fun deleteMatch(id:String) {
        viewModelScope.launch {
            dao.deleteMatch(id)
        }
    }
//
//    fun getMatchById(id:String) : MatchesInfoEntity {
//       return dao.getMatchById(id).
//    }
}
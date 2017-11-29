package com.example.laktionov.githubsearcher.data.source.local.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;
import com.example.laktionov.githubsearcher.data.source.remote.entity.RemoteRepository;

import java.util.List;

@Dao
public interface RepositoryDao {

    @Insert
    void persistData(RepositoryInfo... repositories);

    @Query("DELETE FROM local_reps")
    void deletePersistData();

    @Query("SELECT * FROM local_reps")
    List<RepositoryInfo> getAllLocalData();
}

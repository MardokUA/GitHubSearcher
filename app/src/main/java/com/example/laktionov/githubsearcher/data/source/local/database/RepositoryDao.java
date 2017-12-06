package com.example.laktionov.githubsearcher.data.source.local.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.laktionov.githubsearcher.data.source.local.entity.RepositoryInfo;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void persistData(RepositoryInfo... repositories);

    @Query("DELETE FROM local_reps")
    void deletePersistData();

    @Query("SELECT * FROM local_reps WHERE `query` LIKE :query")
    Single<List<RepositoryInfo>> getCachedLocalDataWithQuery(String query);
}

package com.example.seminar_4;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


@Dao
public interface UserDao {

    /**
     * Get the user from the table. Since for simplicity we only have one user in the database,
     * this query gets all users from the table, but limits the result to just the 1st user.
     *
     * @return the user from the table
     */
    @Query("SELECT * FROM Users WHERE Id=:name")
    User getUser(String name);

    /**
     * Insert a user in the database. If the user already exists, replace it.
     *
     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    /**
     * Delete all users.
     */
    @Query("DELETE FROM Users")
    void deleteAllUsers();

    @Query("DELETE FROM Users WHERE Id=:name")
    void deleteItem(String name);

    @Query("UPDATE Users SET `Limit` = :limit, `Amount` = :amount, `Type` = :type, " +
            "`Category` = :category, `Date` = :date  WHERE Id=:name")
    void updateItem(String name, String limit, String amount, String type, String category, String date);
}

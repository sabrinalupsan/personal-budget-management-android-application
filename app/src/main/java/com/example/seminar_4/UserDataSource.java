package com.example.seminar_4;


import androidx.room.Query;

public class UserDataSource implements UserDao {

    private final UserDao mUserDao;

    public UserDataSource(UserDao userDao) {
        mUserDao = userDao;
    }

    @Override
    public User getUser(String name) {
        return mUserDao.getUser(name);
    }

    @Override
    public void insertUser(User user) {
        mUserDao.insertUser(user);

    }

    @Override
    public void deleteAllUsers() {
        mUserDao.deleteAllUsers();
    }

    @Override
    public void deleteItem(String name) {
        mUserDao.deleteItem(name);
    }

    @Override
    public void updateItem(String name, String limit, String amount, String type, String category, String date) {
        mUserDao.updateItem(name, limit, amount, type, category, date);
    }
}

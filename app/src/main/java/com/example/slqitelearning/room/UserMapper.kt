package com.example.slqitelearning.room

class UserMapper {
    companion object{
        val time = 123
        fun createUser():User{
            val address = Address("Kharkiv", "Tyatralna", 4)
           return User(null,"Nikolay","asd@.com","010212111",address)
        }
    }
}
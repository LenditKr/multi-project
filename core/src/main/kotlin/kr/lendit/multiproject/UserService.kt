package kr.lendit.multiproject

import org.springframework.stereotype.Service
import java.util.LinkedList

class User(
    val name: String,
    val age: Int? = null
)

@Service
class UserService {

    companion object {
        private val users = LinkedList<User>().apply {
            this.add(User("고길동", 42))
        }
    }

    fun getUsers() = users
    fun addUser(user: User) = users.add(user)
}
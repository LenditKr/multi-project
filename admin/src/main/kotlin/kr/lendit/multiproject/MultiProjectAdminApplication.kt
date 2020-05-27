package kr.lendit.multiproject

import mu.KLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class MultiProjectAdminApplication

fun main(args: Array<String>) {
    runApplication<MultiProjectAdminApplication>(*args)
}

@RestController
@RequestMapping("/")
class UserController(
    private val userService: UserService
) {
    companion object: KLogging()

    @GetMapping("/users")
    fun getUsers(): Collection<User> = userService.getUsers()

    @GetMapping("/user")
    fun makeUser(
        @RequestParam name: String,
        @RequestParam age: Int?) {
        logger.info { "Add user: $name, $age" }
        userService.addUser(User(name, age))
    }
}
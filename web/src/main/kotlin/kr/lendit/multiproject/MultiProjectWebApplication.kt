package kr.lendit.multiproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
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
    companion object

    @GetMapping("/users")
    fun getUsers(): Collection<User> = userService.getUsers()
}

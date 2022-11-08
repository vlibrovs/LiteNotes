package com.vlibrovs.litenotes.domain.usecase.user

import com.vlibrovs.litenotes.domain.model.note.Note
import com.vlibrovs.litenotes.domain.model.user.User
import com.vlibrovs.litenotes.domain.repository.Repository
import com.vlibrovs.litenotes.util.auth.AuthResult
import com.vlibrovs.litenotes.util.resource.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class SignInUserUseCaseTest {
    private val repository = object : Repository {
        private val users = mutableListOf<User>(
            User(email = "testuser@email.com", password = "Testpassword123_")
        )
        var currentUser: User? = null

        override suspend fun signInUser(user: User): AuthResult {
            val registeredUser = getUserByEmail(user.email) ?: return AuthResult.NoSuchUser
            return if (user.password == registeredUser.password) {
                currentUser = user
                AuthResult.Success
            } else AuthResult.WrongPassword
        }

        override suspend fun signUpUser(user: User): AuthResult {
            TODO()
        }

        override suspend fun signOutUser(user: User) {
            TODO()
        }

        override suspend fun getUserByEmail(email: String): User? {
            for (user in users) {
                if (user.email == email) return user
            }
            return null
        }

        override suspend fun addNote(user: User, note: Note) {
            TODO()
        }

        override suspend fun deleteNote(user: User, note: Note) {
            TODO()
        }
    }

    private val signInUser = SignInUserUseCase(repository)

    private fun testDataForResult(
        email: String,
        password: String,
        assertion: (Resource<AuthResult>) -> Boolean
    ) = runTest {
        var resource: Resource<AuthResult> = Resource.Empty()
        signInUser(email = email, password = password).collect {
            resource = it
        }
        delay(100)
        assert(assertion(resource))
    }

    @Test
    fun `Empty email test case`() = testDataForResult("", "TestPassword123_") { resource ->
        resource is Resource.Error && resource.data is AuthResult.EmptyEmail
    }

    @Test
    fun `Invalid email test case`() =
        testDataForResult(email = "test", password = "TestPassword123_") { resource ->
            resource is Resource.Error && resource.data is AuthResult.InvalidEmail
        }

    @Test
    fun `Empty password test case`() =
        testDataForResult(email = "test@test.com", password = "") { resource ->
            resource is Resource.Error && resource.data is AuthResult.EmptyPassword
        }

    @Test
    fun `No such user test case`() =
        testDataForResult(email = "test@test.com", password = "TestPassword123_") { resource ->
            resource is Resource.Error && resource.data is AuthResult.NoSuchUser
        }

    @Test
    fun `Wrong password test case`() =
        testDataForResult(
            email = "testuser@email.com",
            password = "Testpassword123__"
        ) { resource ->
            resource is Resource.Error && resource.data is AuthResult.WrongPassword
        }

}

package com.mzazi.harrypotterandroid.domain.usecases

import com.google.common.truth.Truth
import com.mzazi.harrypotterandroid.domain.repo.CharactersRepo
import com.mzazi.harrypotterandroid.fakeMappedCharacters
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import com.mzazi.harrypotterandroid.utils.Result
import org.junit.Assert.*
import org.junit.Test

class SearchCharacterImplUseCaseTest {
    @MockK
    val mockCharactersRepo = mockk<CharactersRepo>()
    @Test
    fun `when we search for character by title, then return success`() = runTest {
        // given
        coEvery { mockCharactersRepo.getCharacters() } returns Result.Success(
            fakeMappedCharacters
        )

        val useCase = createSearchCharacterUseCase(mockCharactersRepo)

        // when
        val result = useCase.invoke(query = "name")

        // then
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        Truth.assertThat((result as Result.Success).data).isNotEmpty()
    }
    @Test
    fun `when we search for character by title and it's not found, then return an empty list`() =
        runTest {
            // given
            coEvery { mockCharactersRepo.getCharacters() } returns Result.Success(
                fakeMappedCharacters
            )

            val useCase = createSearchCharacterUseCase(mockCharactersRepo)

            // when
            val result = useCase.invoke(query = "fake")


            // then
            Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
            Truth.assertThat((result as Result.Success).data).isEmpty()
        }
    private fun createSearchCharacterUseCase(
        repository: CharactersRepo
    ): SearchCharacterUseCase = SearchCharacterImplUseCase(repository = repository)

}
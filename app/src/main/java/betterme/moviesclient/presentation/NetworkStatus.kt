package betterme.moviesclient.presentation

/**
 * Created by Maksym Lazarenko on 2/26/21.
 * Skype: lazexe
 */
sealed class NetworkStatus {

    object InProgress : NetworkStatus()
    object Success : NetworkStatus()
    data class Failed(val errorMessage: String) : NetworkStatus()
}
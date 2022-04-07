import com.gusrinda.marketplace.core.data.source.remote.request.UpdateRequest


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gusrinda.marketplace.core.data.repository.AppRepository
import com.gusrinda.marketplace.core.data.source.remote.request.LoginRequest
import com.gusrinda.marketplace.core.data.source.remote.request.RegisterRequest
import okhttp3.MultipartBody

class UpdateViewModel(val repo : AppRepository) : ViewModel() {

    fun updateUser(id : Int, data : UpdateRequest) = repo.updateUser(id, data).asLiveData()

    fun updateProfilePhoto(id: Int? = null, fileImage: MultipartBody.Part? = null) = repo.updateProfilePhoto(id, fileImage).asLiveData()

}
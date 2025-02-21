package com.example.myapplication.presentaition.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.repositories.getUsersRepositoryImpl
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.domain.models.User
import com.example.myapplication.domain.usecases.AddUserUseCase
import com.example.myapplication.domain.usecases.DeleteUserUseCase
import com.example.myapplication.domain.usecases.GetUsersUseCase
import com.example.myapplication.domain.usecases.UpdateUserUseCase
import com.example.myapplication.presentaition.ui.fragments.UserProfileFragment
import com.example.myapplication.presentaition.viewmodelfactories.AddUserViewModelFactory
import com.example.myapplication.presentaition.viewmodelfactories.DeleteUserViewModelFactory
import com.example.myapplication.presentaition.viewmodelfactories.UpdateUserViewModelFactory
import com.example.myapplication.presentaition.viewmodelfactories.UserViewModelFactory
import com.example.myapplication.presentaition.viewmodels.AddUserViewModel
import com.example.myapplication.presentaition.viewmodels.DeleteUserViewModel
import com.example.myapplication.presentaition.viewmodels.UpdateUserViewModel
import com.example.myapplication.presentaition.viewmodels.UserViewModel
import kotlinx.coroutines.launch

@Suppress("UNREACHABLE_CODE", "DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var addUserViewModel: AddUserViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var updateUserViewModel: UpdateUserViewModel
    private lateinit var deleteUserViewModel: DeleteUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userProfileFragment: UserProfileFragment = UserProfileFragment()

        val userRepository = getUsersRepositoryImpl()
        val addUserUseCase = AddUserUseCase(userRepository)
        val getUsersUseCase = GetUsersUseCase(userRepository)
        val viewModelFactory = UserViewModelFactory(getUsersUseCase)
        val addUserViewModelFactory = AddUserViewModelFactory(addUserUseCase)
        val updateUserUseCase = UpdateUserUseCase(userRepository)
        val updateUserViewModelFactory = UpdateUserViewModelFactory(updateUserUseCase)
        val deleteUseCase = DeleteUserUseCase(userRepository)
        val deleteViewModelFactory = DeleteUserViewModelFactory(deleteUseCase)

        userViewModel = ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]
        addUserViewModel = ViewModelProvider(this, addUserViewModelFactory)[AddUserViewModel::class.java]
        ViewModelProvider(this, updateUserViewModelFactory)[UpdateUserViewModel::class.java]
        updateUserViewModel = ViewModelProvider(this, updateUserViewModelFactory)[UpdateUserViewModel::class.java]
        deleteUserViewModel = ViewModelProvider(this, deleteViewModelFactory)[DeleteUserViewModel::class.java]

        //Обновление
//        binding.buttonId.setOnClickListener{
//            val new_name = binding.editNewNameId.text.toString()
//            val name = binding.editNameId.text.toString()
//            val surname = binding.editSurnameId.text.toString()
//            val age = binding.editAgeId.text.toString()
//
//            if(new_name.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty() && age.isNotEmpty()){
//
//                updateUserViewModel.updateUser(User(name = new_name, surname = surname, age= age.toInt()))
//            }
//            lifecycleScope.launchWhenResumed {
//
//                userViewModel.users.collect() { users ->
//                    displayUsers(users)
//                }
//            }
//        }
//    }
        //Удаление
        binding.buttonId.setOnClickListener {
            val name = binding.editNameId.text.toString()
//            val surname = binding.editSurnameId.text.toString()
//            val age = binding.editAgeId.text.toString().toIntOrNull() // проверяем, что age — число

            if (name.isNotEmpty()) {
                lifecycleScope.launch {
                    deleteUserViewModel.deleteUser(
                        User(
                            name = name,
                            surname = TODO(),
                            age = TODO()
                        )
                    )
                }
            }
        }
    }
    private fun addFragment(fragment: Fragment, containerId: Int?, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            if (supportFragmentManager.findFragmentById(containerId!!) == null) {
                supportFragmentManager
                    .beginTransaction()
                    .add(containerId, fragment)
                    .commitAllowingStateLoss()
            }
        }
    }

    private fun replaceFragment(fragment: Fragment, containerId: Int) {
        if (supportFragmentManager.findFragmentById(containerId) == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(containerId, fragment)
                .commitAllowingStateLoss()
        }
    }

    private fun removeFragment(fragment: Fragment, containerId: Int) {
        if (supportFragmentManager.findFragmentById(containerId) == null) {
            supportFragmentManager
                .beginTransaction()
                .remove(fragment)
                .commitAllowingStateLoss()
        }
    }

    private fun displayUsers(users: List<User>){
        val sb = StringBuilder()
        users.forEach{
            user ->
            sb.append("${user.name}\n")
            Log.d("SecondTag",  user.toString())
        }
        binding.surnameId.text = sb.toString()
    }

}

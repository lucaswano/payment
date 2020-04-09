package com.project.ui.screens.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.base.R
import com.base.fragments.BaseAppFragment
import com.base.util.StyleAnimation
import com.base.util.replace
import com.project.ui.activities.MainActivity
import com.project.ui.preferences.UserPrefs
import com.project.ui.screens.signup.SignUpFragment
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseAppFragment() {
    override val layoutRes = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        signUp.setOnClickListener {
            replace(SignUpFragment(), holder = R.id.holderSplash, animation = StyleAnimation.SLIDE_FROM_RIGHT)
        }

        login.setOnClickListener {
            if (!validateLogin()) {
                showDialogError("All information must be not null", "Error !!!")
                return@setOnClickListener
            }

            disposable.add(dbManager.getListUser()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ listUser ->
                        listUser.find { currentUser ->
                            currentUser.email == edtLoginEmail.text.toString().trim() && currentUser.password == edtLoginPassword.text.toString().trim()
                        }?.let {
                            UserPrefs.get().user = it
                            activity.finish()
                            startActivity(Intent(activity, MainActivity::class.java))
                            return@subscribe
                        }
                        showDialogError("User not existing", "Login failed !!!")
                    }, { error ->
                        //showDialogError("Unknown Error", "Error !!!")
                    }))
        }
    }

    private fun validateLogin() = !TextUtils.isEmpty(edtLoginEmail.text.toString().trim()) && !TextUtils.isEmpty(edtLoginPassword.text.toString().trim())

    private fun showDialogError(msg: String, title: String) {
        AlertDialog.Builder(activity).setTitle(title).setMessage(msg)
                .setNegativeButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }.show()
    }
}
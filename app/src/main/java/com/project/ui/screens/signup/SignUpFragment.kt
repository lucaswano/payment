package com.project.ui.screens.signup
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.base.R
import com.base.fragments.BaseAppFragment
import com.base.util.goBack
import com.project.db.DbManager
import com.project.db.user.User
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_sign_up.*
import java.util.regex.Pattern

class SignUpFragment: BaseAppFragment() {
    companion object{
        val initBalance = arrayListOf(100,200, 500, 1000, 2000, 5000, 10000)
    }
    override val layoutRes = R.layout.fragment_sign_up

    var currentTime = System.currentTimeMillis()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        val policy = getString(R.string.policy)
        val spannableString = SpannableString(policy)
        spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#FEC013")), 37, 51, 0)
        spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#FEC013")), 56, 70, 0)
        textPolicy.text = spannableString
        login.setOnClickListener {
            goBack()
        }

        signUp.setOnClickListener {
            if (!validate()) return@setOnClickListener

            if (!btnPolicy.isChecked){
                showDialogError("You must be agree with our Policy", "Error !!!")
                return@setOnClickListener
            }

            val user = User(email = edtSignUpEmail.text.toString().trim(),
                    password = edtSignUpPassword.text.toString().trim(),
                    userName = "${edtSignUpFirstName.text.toString().trim()} ${edtSignUpLastName.text.toString().trim()}",
                    balance = initBalance[(0..initBalance.size).random()])
            disposable.add(dbManager.getListEmail()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({listEmail ->
                        if (System.currentTimeMillis() - currentTime <= 1000) return@subscribe
                        var isExist = false
                        listEmail.find { email ->
                            email == edtSignUpEmail.text.toString().trim()
                        }?.let {
                            isExist = true
                            return@let
                        }
                        currentTime = System.currentTimeMillis()
                        if (isExist){
                            showDialogError("Email is existing", "Sign up failed !!!")
                        }else{
                            disposable.add(Completable.fromAction{
                                dbManager.createUser(user)
                            }.subscribeOn(Schedulers.io()).subscribe())

                            showDialogSuccess()
                            return@subscribe
                        }
                    }, {error ->
                        Log.e("Error", error.printStackTrace().toString())
                    }))
        }
    }

    private fun validate(): Boolean{
        if (TextUtils.isEmpty(edtSignUpEmail.text.toString().trim()) || TextUtils.isEmpty(edtSignUpPassword.text.toString().trim())
                || TextUtils.isEmpty(edtSignUpFirstName.text.toString().trim()) || TextUtils.isEmpty(edtSignUpLastName.text.toString().trim())) return false

        if (!validateEmail()) {
            showDialogError("Invalidate email format", "Error !!!")
            return false
        }

        if (!validatePassword()) {
            showDialogError("Invalidate password format", "Error !!!")
            return false
        }
        return true
    }

    private fun validateEmail() = Patterns.EMAIL_ADDRESS.matcher(edtSignUpEmail.text.toString().trim()).matches()

    private fun validatePassword(): Boolean{
        val PATTERN_PASSWORD = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})"
        if (edtSignUpPassword.text.toString().trim().length < 8) return false

        val pattern = Pattern.compile(PATTERN_PASSWORD)
        return pattern.matcher(edtSignUpPassword.text.toString().trim()).matches()
    }

    private fun showDialogError(msg: String, title: String){
        AlertDialog.Builder(activity).setTitle(title).setMessage(msg)
                .setNegativeButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }.show()
    }

    private fun showDialogSuccess(){
        val dialog = AlertDialog.Builder(activity).setTitle("Sign up success !!!")
                .setMessage("Your account was created.")
                .setNegativeButton("Back"){dialog, _ ->
                    dialog.dismiss()
                    goBack()
                }
        dialog.setCancelable(false)
        dialog.show()
    }
}
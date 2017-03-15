package com.majavrella.bloodfactory;


import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.majavrella.bloodfactory.appbase.BaseFragment;
import com.majavrella.bloodfactory.base.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SigninFragment extends BaseFragment {

    private static View mSigninFragment;
    @Bind(R.id.user_mob) EditText mUserMobile;
    @Bind(R.id.user_password) EditText mUserPassword;
    @Bind(R.id.show_password) CheckBox mShowPassword;
    @Bind(R.id.signin) Button mSignin;
    @Bind(R.id.textRegister) TextView mTextRegister;
    @Bind(R.id.lostPassword) TextView mLostPassword;
    @Bind(R.id.back) FrameLayout mBack;

    public static SigninFragment newInstance() {
        return new SigninFragment();
    }

    public SigninFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mSigninFragment = inflater.inflate(R.layout.login_fragment, container, false);
        ButterKnife.bind(this, mSigninFragment);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(mActivity);
                add(FirstFragment.newInstance()); }
        });
        mSignin.setOnClickListener(mSigninListener);
        mTextRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(RegisterFragment.newInstance());}
        });

        mShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mUserPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    mUserPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        mLostPassword.setOnClickListener(mLostPasswordListener);

        return mSigninFragment;
    }

    private boolean dataValidation() {
        boolean validation = true;
        if(!isPhoneValid(mUserMobile.getText().toString())){
            validation = false;
            mUserMobile.setError(Constants.mobErrorText);
        }
        if(mUserPassword.getText().toString().trim().length()<6){
            validation = false;
            mUserPassword.setError("Enter more than 6 characters");
        }
        return validation;
    }

    @Override
    public void onResume() {
        hideKeyboard(mActivity);
        super.onResume();
    }

    View.OnClickListener mSigninListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean isAllFieldsValid = dataValidation();
            if (isAllFieldsValid){
                progress.setMessage("Validating user...");
                progress.show();
                progress.dismiss();
                /*String email = userName.getText().toString();
                String password = userPassword.getText().toString();
                email = email.trim();
                password = password.trim();
                mFirebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progress.dismiss();
                                    Intent intent = new Intent(mActivity, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    Toast.makeText(mActivity, " Successfully login!!!", Toast.LENGTH_SHORT).show();
                                } else {
                                    progress.dismiss();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                                    builder.setMessage(task.getException().getMessage())
                                            .setTitle(R.string.login_error_title)
                                            .setPositiveButton(android.R.string.ok, null);
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                }
                            }
                        });*/
            } else {
                progress.dismiss();
                Toast.makeText(mActivity, "Validation Error!!!", Toast.LENGTH_SHORT).show();
            }

        }
    };

    View.OnClickListener mLostPasswordListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), "Lost password cliked!!", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected String getTitle() {
        return Constants.kLoginFragment;
    }
}

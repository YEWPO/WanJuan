package com.uestc.wanjuan;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class SigninFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (ParseUser.getCurrentUser() != null) {
            Navigation.findNavController(view).navigate(R.id.signin);
        }

        ImageView iconView = view.findViewById(R.id.signinicon);
        iconView.setImageResource(R.drawable.icon);
        TextView usernameTextView = view.findViewById(R.id.signinusernametext);
        TextView passwordTextView = view.findViewById(R.id.signinpasswordtext);

        Button signinButton = view.findViewById(R.id.signinbutton);
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameTextView.getText().toString();
                String password = passwordTextView.getText().toString();

                if (username.equals("") || password.equals("")) {
                    Toast.makeText(view.getContext(), "empty username or password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e == null) {
                            Toast.makeText(view.getContext(), "Log in success!", Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(view).navigate(R.id.signin);
                        } else {
                            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        TextView signupButton = view.findViewById(R.id.signuptext);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.signup);
            }
        });
    }
}

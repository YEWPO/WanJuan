package com.uestc.wanjuan;

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

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView iconView = view.findViewById(R.id.signupicon);
        iconView.setImageResource(R.drawable.icon);

        TextView backText = view.findViewById(R.id.backtext);
        backText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.backsignin);
            }
        });

        TextView usernameText = view.findViewById(R.id.signupusernametext);
        TextView passwordText = view.findViewById(R.id.signuppasswordtext);
        TextView password2Text = view.findViewById(R.id.signuppassword2Text);

        Button signupButton = view.findViewById(R.id.signupbotton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameText.getText().toString();
                String password1 = passwordText.getText().toString();
                String password2 = password2Text.getText().toString();

                if (username.equals("") || password1.equals("")) {
                    Toast.makeText(view.getContext(), "Empty username or password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password1.equals(password2)) {
                    Toast.makeText(view.getContext(), "reput password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                ParseUser parseUser = new ParseUser();
                parseUser.setUsername(username);
                parseUser.setPassword(password1);
                parseUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(view.getContext(), "Sign up success!", Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(view).navigate(R.id.backsignin);
                        } else {
                            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}

package com.example.authtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.amplifyframework.api.aws.GsonVariablesSerializer;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.api.graphql.SimpleGraphQLRequest;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Profile;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;


import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpCookie;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileUpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);


        createProfileMain();

        Button signOut = findViewById(R.id.sign_out);
        signOut.setOnClickListener(v -> {
            Amplify.Auth.signOut(
                    AuthSignOutOptions.builder().globalSignOut(true).build(),
                    () -> {
                        Log.i("AuthQuickstart", "Signed out successfully");
                        toastMsg("Signed out successfully");
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    },
                    error -> {
                        Log.e("AuthQuickstart", error.toString());
                        toastMsg(error.getMessage());
                    }
            );
        });
    }
    // private final String identityId = getIntent().getStringExtra("identityId");

    public void toastMsg(String msg) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                runOnUiThread(() -> Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show());
            }
        };
        thread.start();
    }

    private void createProfile(String identityID, String email) {

        Profile profile = Profile.builder().email(email).owner(identityID).build();
        Amplify.API.mutate(ModelMutation.create(profile),
                response -> {
                    Log.i("MyAmplifyApp", "Profile with id: " + response.getData().getId());
                    toastMsg("Profile created");
                },
                error -> {
                    Log.e("MyAmplifyApp", "Create failed", error);
                    toastMsg(error.getMessage());
                });
    }

    public GraphQLRequest<String> getByProfileOwner(String owner) {
        String document = "query ProfileByOwner( $owner: String) {\n" +
                "  profileByOwner(owner: $owner) {\n" +
                "    items {\n" +
                "      id\n" +
                "      owner\n" +
                "    }\n" +
                "  }\n" +
                "}";

        return new SimpleGraphQLRequest<>(
                document,
                Collections.singletonMap("owner", owner),
                JsonObject.class,
                new GsonVariablesSerializer()
        );

    }

    private boolean isProfileCreated(GraphQLResponse gqlResponse) {
        List items = null;
        try{
            Gson gson = new Gson();
            Object h = gqlResponse.getData();
            JsonObject data = (JsonObject) h;
            Map map = gson.fromJson(data, Map.class);
            System.out.println(map);
            Map profileByOwner = (Map) map.get("profileByOwner");
            System.out.println(profileByOwner);
            items = (List) profileByOwner.get("items");
            System.out.println(items);

        }
        catch (ClassCastException e){
            System.out.println(e.getMessage());
            toastMsg("Error. Please try again later");
        }

        if (items == null) {
            return false;
        }
        else {

            toastMsg("Profile already created");
            return true;
        }
    }


    private void createProfileMain() {
        Amplify.Auth.fetchUserAttributes(attributes -> {
                    Log.i("AuthDemo", "User attributes = " + attributes.get(0).getValue());
                    String owner = attributes.get(0).getValue();
                    String email = attributes.get(2).getValue();

                    // createProfile(owner, email);

                    Amplify.API.query(getByProfileOwner(owner),
                            response -> {
                                Log.d("MyAmplifyApp", "Response: " + response);
                                if(!isProfileCreated(response)) {
                                    createProfile(owner, email);
                                }
                            },
                            error -> {
                                Log.e("MyAmplifyApp", "Error", error);
                                toastMsg(error.getMessage());
                            });

                },
                error -> Log.e("AuthDemo", "Failed to fetch user attributes.", error));


        // return true if response and return false if error

    }


}
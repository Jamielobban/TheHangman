// Generated by view binder compiler. Do not edit!
package com.example.hangmannewgame.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.hangmannewgame.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityNameInputBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView gameOverText;

  @NonNull
  public final ImageView imageView9;

  @NonNull
  public final TextInputEditText nameChange;

  @NonNull
  public final TextView scoreText2;

  @NonNull
  public final TextView streakText;

  @NonNull
  public final TextInputLayout textInputLayout2;

  private ActivityNameInputBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView gameOverText, @NonNull ImageView imageView9,
      @NonNull TextInputEditText nameChange, @NonNull TextView scoreText2,
      @NonNull TextView streakText, @NonNull TextInputLayout textInputLayout2) {
    this.rootView = rootView;
    this.gameOverText = gameOverText;
    this.imageView9 = imageView9;
    this.nameChange = nameChange;
    this.scoreText2 = scoreText2;
    this.streakText = streakText;
    this.textInputLayout2 = textInputLayout2;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityNameInputBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityNameInputBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_name_input, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityNameInputBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.gameOverText;
      TextView gameOverText = ViewBindings.findChildViewById(rootView, id);
      if (gameOverText == null) {
        break missingId;
      }

      id = R.id.imageView9;
      ImageView imageView9 = ViewBindings.findChildViewById(rootView, id);
      if (imageView9 == null) {
        break missingId;
      }

      id = R.id.nameChange;
      TextInputEditText nameChange = ViewBindings.findChildViewById(rootView, id);
      if (nameChange == null) {
        break missingId;
      }

      id = R.id.scoreText2;
      TextView scoreText2 = ViewBindings.findChildViewById(rootView, id);
      if (scoreText2 == null) {
        break missingId;
      }

      id = R.id.streakText;
      TextView streakText = ViewBindings.findChildViewById(rootView, id);
      if (streakText == null) {
        break missingId;
      }

      id = R.id.textInputLayout2;
      TextInputLayout textInputLayout2 = ViewBindings.findChildViewById(rootView, id);
      if (textInputLayout2 == null) {
        break missingId;
      }

      return new ActivityNameInputBinding((ConstraintLayout) rootView, gameOverText, imageView9,
          nameChange, scoreText2, streakText, textInputLayout2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

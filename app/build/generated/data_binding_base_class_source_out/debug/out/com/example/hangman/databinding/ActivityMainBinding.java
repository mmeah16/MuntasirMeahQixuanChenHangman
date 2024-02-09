// Generated by view binder compiler. Do not edit!
package com.example.hangman.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.hangman.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final FragmentContainerView fragmentContainer1;

  @NonNull
  public final FragmentContainerView fragmentContainer2;

  private ActivityMainBinding(@NonNull LinearLayout rootView,
      @NonNull FragmentContainerView fragmentContainer1,
      @NonNull FragmentContainerView fragmentContainer2) {
    this.rootView = rootView;
    this.fragmentContainer1 = fragmentContainer1;
    this.fragmentContainer2 = fragmentContainer2;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.fragment_container1;
      FragmentContainerView fragmentContainer1 = ViewBindings.findChildViewById(rootView, id);
      if (fragmentContainer1 == null) {
        break missingId;
      }

      id = R.id.fragment_container2;
      FragmentContainerView fragmentContainer2 = ViewBindings.findChildViewById(rootView, id);
      if (fragmentContainer2 == null) {
        break missingId;
      }

      return new ActivityMainBinding((LinearLayout) rootView, fragmentContainer1,
          fragmentContainer2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

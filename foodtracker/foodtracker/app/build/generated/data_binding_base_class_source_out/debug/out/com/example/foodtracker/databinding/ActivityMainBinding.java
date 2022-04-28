// Generated by view binder compiler. Do not edit!
package com.example.foodtracker.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.foodtracker.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final BottomNavigationView bottomNavigationView;

  @NonNull
  public final RelativeLayout container;

  @NonNull
  public final FrameLayout mainContainer;

  private ActivityMainBinding(@NonNull RelativeLayout rootView,
      @NonNull BottomNavigationView bottomNavigationView, @NonNull RelativeLayout container,
      @NonNull FrameLayout mainContainer) {
    this.rootView = rootView;
    this.bottomNavigationView = bottomNavigationView;
    this.container = container;
    this.mainContainer = mainContainer;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
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
      id = R.id.bottomNavigationView;
      BottomNavigationView bottomNavigationView = ViewBindings.findChildViewById(rootView, id);
      if (bottomNavigationView == null) {
        break missingId;
      }

      RelativeLayout container = (RelativeLayout) rootView;

      id = R.id.main_container;
      FrameLayout mainContainer = ViewBindings.findChildViewById(rootView, id);
      if (mainContainer == null) {
        break missingId;
      }

      return new ActivityMainBinding((RelativeLayout) rootView, bottomNavigationView, container,
          mainContainer);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

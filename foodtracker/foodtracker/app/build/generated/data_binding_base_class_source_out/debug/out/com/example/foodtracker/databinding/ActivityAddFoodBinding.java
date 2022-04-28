// Generated by view binder compiler. Do not edit!
package com.example.foodtracker.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.foodtracker.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAddFoodBinding implements ViewBinding {
  @NonNull
  private final AppBarLayout rootView;

  @NonNull
  public final Button AddDateButton;

  @NonNull
  public final Spinner SpinnerPlaceFood;

  @NonNull
  public final Button buttonAddFood;

  @NonNull
  public final Button buttonBack;

  @NonNull
  public final EditText editDateExpirationFood;

  @NonNull
  public final EditText editFoodNameFood;

  @NonNull
  public final EditText editTextQuantityFood;

  @NonNull
  public final CardView personalDetailsCard;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextInputLayout txtInputDateExpiration;

  @NonNull
  public final TextInputLayout txtInputFood;

  @NonNull
  public final TextInputLayout txtInputPlace;

  @NonNull
  public final TextInputLayout txtInputQuantity;

  private ActivityAddFoodBinding(@NonNull AppBarLayout rootView, @NonNull Button AddDateButton,
      @NonNull Spinner SpinnerPlaceFood, @NonNull Button buttonAddFood, @NonNull Button buttonBack,
      @NonNull EditText editDateExpirationFood, @NonNull EditText editFoodNameFood,
      @NonNull EditText editTextQuantityFood, @NonNull CardView personalDetailsCard,
      @NonNull TextView textView, @NonNull TextInputLayout txtInputDateExpiration,
      @NonNull TextInputLayout txtInputFood, @NonNull TextInputLayout txtInputPlace,
      @NonNull TextInputLayout txtInputQuantity) {
    this.rootView = rootView;
    this.AddDateButton = AddDateButton;
    this.SpinnerPlaceFood = SpinnerPlaceFood;
    this.buttonAddFood = buttonAddFood;
    this.buttonBack = buttonBack;
    this.editDateExpirationFood = editDateExpirationFood;
    this.editFoodNameFood = editFoodNameFood;
    this.editTextQuantityFood = editTextQuantityFood;
    this.personalDetailsCard = personalDetailsCard;
    this.textView = textView;
    this.txtInputDateExpiration = txtInputDateExpiration;
    this.txtInputFood = txtInputFood;
    this.txtInputPlace = txtInputPlace;
    this.txtInputQuantity = txtInputQuantity;
  }

  @Override
  @NonNull
  public AppBarLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAddFoodBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAddFoodBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_add_food, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAddFoodBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.AddDate_Button;
      Button AddDateButton = ViewBindings.findChildViewById(rootView, id);
      if (AddDateButton == null) {
        break missingId;
      }

      id = R.id.Spinner_Place_food;
      Spinner SpinnerPlaceFood = ViewBindings.findChildViewById(rootView, id);
      if (SpinnerPlaceFood == null) {
        break missingId;
      }

      id = R.id.buttonAddFood;
      Button buttonAddFood = ViewBindings.findChildViewById(rootView, id);
      if (buttonAddFood == null) {
        break missingId;
      }

      id = R.id.buttonBack;
      Button buttonBack = ViewBindings.findChildViewById(rootView, id);
      if (buttonBack == null) {
        break missingId;
      }

      id = R.id.editDateExpiration_food;
      EditText editDateExpirationFood = ViewBindings.findChildViewById(rootView, id);
      if (editDateExpirationFood == null) {
        break missingId;
      }

      id = R.id.editFoodName_food;
      EditText editFoodNameFood = ViewBindings.findChildViewById(rootView, id);
      if (editFoodNameFood == null) {
        break missingId;
      }

      id = R.id.editTextQuantity_food;
      EditText editTextQuantityFood = ViewBindings.findChildViewById(rootView, id);
      if (editTextQuantityFood == null) {
        break missingId;
      }

      id = R.id.personalDetailsCard;
      CardView personalDetailsCard = ViewBindings.findChildViewById(rootView, id);
      if (personalDetailsCard == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.txtInputDateExpiration;
      TextInputLayout txtInputDateExpiration = ViewBindings.findChildViewById(rootView, id);
      if (txtInputDateExpiration == null) {
        break missingId;
      }

      id = R.id.txtInputFood;
      TextInputLayout txtInputFood = ViewBindings.findChildViewById(rootView, id);
      if (txtInputFood == null) {
        break missingId;
      }

      id = R.id.txtInputPlace;
      TextInputLayout txtInputPlace = ViewBindings.findChildViewById(rootView, id);
      if (txtInputPlace == null) {
        break missingId;
      }

      id = R.id.txtInputQuantity;
      TextInputLayout txtInputQuantity = ViewBindings.findChildViewById(rootView, id);
      if (txtInputQuantity == null) {
        break missingId;
      }

      return new ActivityAddFoodBinding((AppBarLayout) rootView, AddDateButton, SpinnerPlaceFood,
          buttonAddFood, buttonBack, editDateExpirationFood, editFoodNameFood, editTextQuantityFood,
          personalDetailsCard, textView, txtInputDateExpiration, txtInputFood, txtInputPlace,
          txtInputQuantity);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

package com.arogyaniidhi.app.ui.documents

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import com.arogyaniidhi.app.`data`.model.HealthScheme
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class DocumentGuideFragmentArgs(
  public val scheme: HealthScheme,
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toBundle(): Bundle {
    val result = Bundle()
    if (Parcelable::class.java.isAssignableFrom(HealthScheme::class.java)) {
      result.putParcelable("scheme", this.scheme as Parcelable)
    } else if (Serializable::class.java.isAssignableFrom(HealthScheme::class.java)) {
      result.putSerializable("scheme", this.scheme as Serializable)
    } else {
      throw UnsupportedOperationException(HealthScheme::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    return result
  }

  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    if (Parcelable::class.java.isAssignableFrom(HealthScheme::class.java)) {
      result.set("scheme", this.scheme as Parcelable)
    } else if (Serializable::class.java.isAssignableFrom(HealthScheme::class.java)) {
      result.set("scheme", this.scheme as Serializable)
    } else {
      throw UnsupportedOperationException(HealthScheme::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    return result
  }

  public companion object {
    @JvmStatic
    @Suppress("DEPRECATION")
    public fun fromBundle(bundle: Bundle): DocumentGuideFragmentArgs {
      bundle.setClassLoader(DocumentGuideFragmentArgs::class.java.classLoader)
      val __scheme : HealthScheme?
      if (bundle.containsKey("scheme")) {
        if (Parcelable::class.java.isAssignableFrom(HealthScheme::class.java) ||
            Serializable::class.java.isAssignableFrom(HealthScheme::class.java)) {
          __scheme = bundle.get("scheme") as HealthScheme?
        } else {
          throw UnsupportedOperationException(HealthScheme::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__scheme == null) {
          throw IllegalArgumentException("Argument \"scheme\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"scheme\" is missing and does not have an android:defaultValue")
      }
      return DocumentGuideFragmentArgs(__scheme)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): DocumentGuideFragmentArgs {
      val __scheme : HealthScheme?
      if (savedStateHandle.contains("scheme")) {
        if (Parcelable::class.java.isAssignableFrom(HealthScheme::class.java) ||
            Serializable::class.java.isAssignableFrom(HealthScheme::class.java)) {
          __scheme = savedStateHandle.get<HealthScheme?>("scheme")
        } else {
          throw UnsupportedOperationException(HealthScheme::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__scheme == null) {
          throw IllegalArgumentException("Argument \"scheme\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"scheme\" is missing and does not have an android:defaultValue")
      }
      return DocumentGuideFragmentArgs(__scheme)
    }
  }
}

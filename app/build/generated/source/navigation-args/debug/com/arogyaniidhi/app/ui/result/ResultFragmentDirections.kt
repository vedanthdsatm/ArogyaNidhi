package com.arogyaniidhi.app.ui.result

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.arogyaniidhi.app.R
import com.arogyaniidhi.app.`data`.model.HealthScheme
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class ResultFragmentDirections private constructor() {
  private data class ActionResultFragmentToDocumentGuideFragment(
    public val scheme: HealthScheme,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_resultFragment_to_documentGuideFragment

    public override val arguments: Bundle
      @Suppress("CAST_NEVER_SUCCEEDS")
      get() {
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
  }

  public companion object {
    public fun actionResultFragmentToDocumentGuideFragment(scheme: HealthScheme): NavDirections =
        ActionResultFragmentToDocumentGuideFragment(scheme)

    public fun actionResultFragmentToHospitalListFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_resultFragment_to_hospitalListFragment)

    public fun actionResultFragmentToEligibilityQuizFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_resultFragment_to_eligibilityQuizFragment)
  }
}

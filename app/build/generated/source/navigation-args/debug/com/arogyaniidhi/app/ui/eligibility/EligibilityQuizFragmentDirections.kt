package com.arogyaniidhi.app.ui.eligibility

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.arogyaniidhi.app.R

public class EligibilityQuizFragmentDirections private constructor() {
  public companion object {
    public fun actionEligibilityQuizFragmentToResultFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_eligibilityQuizFragment_to_resultFragment)
  }
}

@registration
Feature: Registration Flow

	Background:
		Given user is on welcome page

	@smoke @critical
	Scenario: Complete registration flow successfully
		When user accepts cookies if present
		And user clicks start button
		Then user should be on card one page
		When user fills credentials and proceeds
		Then user should be on card two page
		When user selects interests
		And user uploads an image using explorer
		And user clicks next on card two page
		Then user should be on card three page
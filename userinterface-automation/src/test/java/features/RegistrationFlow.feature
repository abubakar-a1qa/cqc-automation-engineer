@registration
Feature: Registration Flow

	Background:
		Given the user is on the welcome page

	@smoke @critical
	Scenario: Complete registration flow successfully
		When the user accepts cookies if present
		And the user clicks the start button
		Then the user should be on card one page
		When the user fills credentials and proceeds
		Then the user should be on card two page
		When the user selects interests
		And the user uploads an image using explorer
		And the user clicks next on card two page
		Then the user should be on card three page
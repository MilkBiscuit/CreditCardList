
Create an Android app that uses the below API:
https://random-data-api.com/api/v2/credit_cards?size=100

- Fetch the cards and display them in a list.
- For each card, show the card type, card number, and expiry date.
- Feel free to use Compose or XML views, depending on your level of experience with Compose.
- Please include a Readme file to list all assumptions and decisions.

# Notes
1. The requirement is quite simple, barely has any business logic, so didn't add unit test, no UI test either.
2. Assume the API always works fine, didn't add any UI for API service down and it fails to get any data.
   In fact, if the API is called two consecutive times in a short period (if you scroll too fast in the list, that is),
   the second API call will fail, "Retry later", didn't handle this scenario.
3. The API returns different random data for every call, which is quite unexpected.
4. Ignore about the localisation, all strings are hardcoded.
5. Padding, sizes are hardcoded too, it is recommended to put these commonly used constants together in one file.


Create an Android app that uses the below API:
https://random-data-api.com/api/v2/credit_cards?size=100

- Fetch the cards and display them in a list.
- For each card, show the card type, card number, and expiry date.
- Feel free to use Compose or XML views, depending on your level of experience with Compose.
- Please include a Readme file to list all assumptions and decisions.

# Notes
1. The requirement is quite simple, barely has any business logic, so only wrote a unit test for
   parsing objects from json string, and an integration test of GET https://random-data-api.com/api/v2/credit_cards?size=10
2. Assume the API always works fine, didn't add any UI for API service down and it fails to get any data.
   In fact, if the API is called two consecutive times in a short period (if you scroll too fast in the list, that is),
   the second API call will fail as "Retry later", didn't handle this scenario.
3. Ignored the localisation, all strings are hardcoded.
4. Padding, sizes are hardcoded too, but it is recommended to put these commonly used constants together in one place.
5. Just FYI, I didn't use [Compose Pager](https://developer.android.com/develop/ui/compose/layouts/pager) but it quite suits this screen,
   we could have a page indicator, cool animation, page size configurables and caches.

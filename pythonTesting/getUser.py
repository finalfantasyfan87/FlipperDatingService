import random
import time
import requests

API_URL = "http://localhost:9090/userapi/user"

get_user_url = API_URL + "/{userId}"
get_users_url = API_URL+"/users"
get_user_by_username_url = API_URL + "/name/{userName}"
get_user_by_zodiac_url = API_URL + "/zodiac/{zodiacSign}"


def get_user(userId):
    response = requests.get(get_user_url.format(userId=userId), timeout=10)

    if response.status_code == 200:
        data = response.json()
        print(f"User data for userId {userId}: {data}")
    else:
        print(f"Request failed: {response.status_code}")
        print(response.text)
        
        
def get_users():
    response = requests.get(get_users_url, timeout=10)

    if response.status_code == 200:
        data = response.json()
        print(f"All users data: {data}")
    else:
        print(f"Request failed: {response.status_code}")
        print(response.text)


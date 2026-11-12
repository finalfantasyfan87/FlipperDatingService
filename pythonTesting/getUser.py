import random
import time
import requests



API_URL = "http://localhost:9090/userapi"

get_user_url = API_URL + "/user/"
get_users_url = API_URL + "/users"
get_user_by_username_url = API_URL + "/user/name/{userName}"
get_user_by_zodiac_url = API_URL + "/user/zodiac/{zodiacSign}"


def get_user(userId):
    response = requests.get(get_user_url+userId, timeout=10)

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
        print(requests.get(get_users_url))
        print(f"All users data: {data}")
    else:
        print(f"Request failed: {response.status_code}")
        print(response.text)

#get_user("ac9cc3f0-b915-4a6a-a38a-4fef34f0bddc")  # Replace with a valid userId
get_users()  # Call the get_users function to retrieve all users
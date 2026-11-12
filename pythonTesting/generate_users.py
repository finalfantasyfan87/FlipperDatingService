import random
import time
import requests
from faker import Faker

fake = Faker()

API_URL = "http://localhost:9090/userapi/addUser"

zodiac_signs = [
    "aries", "taurus", "gemini", "cancer", "leo", "virgo",
    "libra", "scorpio", "sagittarius", "capricorn", "aquarius", "pisces"
]

interests_pool = [
    "hiking", "travel", "reading", "music", "coffee",
    "movies", "cooking", "fitness", "photography", "gaming",
    "skiing", "art", "dancing", "writing", "running"
]


def generate_user():
    gender = random.choice(["female", "male", "nonbinary"])
    username = fake.user_name() + str(random.randint(100, 999))

    return {
        "email": fake.email(),
        "userName": username,
        "profilePictureUrl": fake.image_url(),
        "password": fake.password(length=12, special_chars=True, digits=True, upper_case=True),
        "gender": gender,
        "interests": random.sample(interests_pool, random.randint(3, 5)),
        "zodiacSign": random.choice(zodiac_signs),
        "conversationIds": []
    }


def create_user():
    payload = generate_user()
    response = requests.post(API_URL, json=payload, timeout=10)

    if response.status_code in (200, 201):
        data = response.json()
        user_id = data.get("id", "unknown")
        print(f"{user_id} has been created")
    else:
        print(f"Request failed: {response.status_code}")
        print(response.text)


for _ in range(10):
    create_user()
    time.sleep(0.5)

import discord
import random
import time
import re

def random_delay(min_delay=1, max_delay=2):
    delay = random.uniform(min_delay, max_delay)
    time.sleep(delay)


SERVER_ID = 669676558167965713
USER_ID = 901252208631373854
NERD_EMOJI = '🤓'

def extract_the_string(input_string):
    # Regex pattern to match the desired format
    pattern = r'`([^`]+)`'

    # Search for the pattern in the input string
    match = re.search(pattern, input_string)

    if match:
        # Extract and return the content of THESTRING
        return match.group(1)
    else:
        return None  # Return None if no match is found


class MyClient(discord.Client):
    async def on_ready(self):
        print('Logged on as', self.user)

    async def on_message(self,message):

        if message.author.id == USER_ID:

            if message.embeds:

                embed = message.embeds[0]

                channel_id = message.channel.id

                embed_title = embed.title
                embed_description = embed.description

                print(f"Embed Description: {embed_description}")
                print(f"Embed Title: {embed_title}")

                print(str(message.channel.id))

                if "***Type " in embed_description:
                    print("found trigger DESCRIPTION string")

                    if "***You " in embed_title:
                        print("found trigger DESCRIPTION string")

                        pattern = r'Type `([^`]+)` to collect it'
                        text = embed_description

                        #match = re.search(pattern, text)
                        result = extract_the_string(embed_description)

                        print(result)
                        print("FOUND!!!")
                        random_delay()
                        await message.channel.send(str(result))
            else:
                print("No embeds found in the message.")

client = MyClient()
client.run('IHR TOKEN HIER')

class PostOffice:
    """A Post Office class. Allows users to message each other.

    :ivar int message_id: Incremental id of the last message sent.
    :ivar dict boxes: Users' inboxes.

    :param list usernames: Users for which we should create PO Boxes.
    """

    def __init__(self, usernames):
        self.message_id = 0
        self.boxes = {user: [] for user in usernames}

    def send_message(self, sender, recipient, message_body, urgent=False):
        """Send a message to a recipient.

        :param str sender: The message sender's username.
        :param str recipient: The message recipient's username.
        :param str message_body: The body of the message.
        :param urgent: The urgency of the message.
        :type urgent: bool, optional
        :return: The message ID, auto incremented number.
        :rtype: int
        :raises KeyError: if the recipient does not exist.
        """
        user_box = self.boxes[recipient]
        self.message_id = self.message_id + 1
        message_details = {
            'id': self.message_id,
            'body': message_body,
            'sender': sender,
            'status' : 'unread' # this allows to sort messages which are read and unread
        }
        if urgent:
            user_box.insert(0, message_details)
        else:
            user_box.append(message_details)
        return self.message_id

    def search_inbox(self, username, string):
        """
        Search for all messages which contain string
        :param str username:
        :param str string:
        :return: a list of matching messages
        """
        list_of_messages = self.boxes[username]
        return [message for message in list_of_messages if string in message.get('body')]

    def read_inbox(self, username, n=0):
        """
        Returns n first messages in user's inbox. Marks them as read
        :param str username:
        :param int n:
        :return: the list of n first messages or all of them if n not provided
        """
        n_messages = [] #initialize a returning list

        if n == 0:
            n = len(self.boxes[username])   #if n not provided, we will return all messages

        for i in range(n):
            message = self.boxes[username][i]
            message_copy = message.copy()       #a copy of message for it to be displayed as unread
            n_messages += [message_copy]        #add it to returning list
            message['status'] = 'read'          #change status to read in the original list
            self.boxes[username][i] = message

        return n_messages

if __name__ == '__main__':
    post_office = PostOffice(['John', 'Jane', 'Mary'])
    post_office.send_message('John', 'Mary', 'Hello Mary')
    post_office.send_message('Mary', 'John', 'Hello John')
    post_office.send_message('John', 'Mary', 'How is it going?')
    print(post_office.read_inbox('Mary'))
    print(post_office.search_inbox('Mary', 'Hello'))











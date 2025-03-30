import random
from tkinter import *
from PIL import Image, ImageTk
class Game:
    def __init__(self):
        self.window = Tk()
        self.canvas = Canvas(self.window)
        self.topics = ["food", "countries", "animals", "school"]
        self.window.attributes("-fullscreen",True)
        self.window.bind("<Escape>", self.openMenu)
        self.window.title("Hangman")
        self.copybook = ImageTk.PhotoImage(Image.open("copybook.jpg"))
        self.hangmanPic = PhotoImage(file='hangman.png')
        self.background = Label(self.window,image=self.copybook)
        self.label = Label(self.canvas,text="Hangman",bg="beige",font=("Ink Free",40,'bold','italic','overstrike'),compound='bottom', relief="groove", bd=5)
        self.playButton = Button(self.canvas, text="Play", font=("Ink Free", 30), command=self.play, relief="solid", bd=3, bg="beige", activebackground="beige")
        self.exitButton = Button(self.canvas, text="Exit", font=("Ink Free", 30), command=self.closeGame, relief="solid", bd=3, bg="beige", activebackground="beige")
        self.listOfLines = []
    def openMenu(self, Event=None):
        self.listOfLines.clear()
        for widget in self.canvas.winfo_children():
            widget.pack_forget()
            widget.grid_forget()
        self.canvas.pack(fill="both",expand=True)
        self.canvas.create_image(0,0,image=self.copybook, anchor="nw")
        self.label.config(image=self.hangmanPic, pady=25)
        self.label.pack(anchor='n',pady=(50,0))
        self.playButton.pack(anchor='s',pady=(50,50))
        self.exitButton.pack(anchor='s',pady=(0,50))
        self.window.mainloop()
    def closeGame(self, Event=None):
        self.window.destroy()
        exit(0)
    def play(self):
        self.playButton.pack_forget()
        self.exitButton.pack_forget()
        global topic
        topic = IntVar()
        choose_topic_label = Label(self.canvas, text="Choose a topic: ", bg="beige", font=("Ink Free", 25), relief="groove", bd=5)
        choose_topic_label.pack(pady=(30, 10))
        frame = Frame(self.canvas)
        frame.pack(pady=(40,10))
        for i in range(len(self.topics)):
            button = Radiobutton(frame, text=self.topics[i], indicatoron=False, selectcolor="beige", font=("Ink Free",16), relief="solid", bd=1,bg="beige", activebackground="beige", variable=topic, padx=20, pady=15, value=i, width=10)
            button.pack(side="left")
        startButton = Button(self.canvas, text="Start", font=("Ink Free", 30), command=self.startGame, relief="solid", bd=3, bg="beige", activebackground="beige")
        startButton.pack(anchor="s")
    def InWord(self, Event, word):
        if self.lineChooser <7:
            wordCoordX, wordCoordY = self.canvas.coords(self.displayedWord)
            wordCoordX -= 100
            letter = Event.keysym.upper()
            if letter.isalpha() and len(letter)==1 and letter not in self.pressedList:
                self.pressedList.append(letter)
                if letter in word:
                    newWord = self.updateWord(letter.upper(), word)
                    self.hiddenWord = newWord
                    self.canvas.itemconfig(self.displayedWord, text=newWord)
                    if (self.hiddenWord == word):
                        self.canvas.create_text(wordCoordX + 100, wordCoordY + 100, text="YOU WIN!", font=("Consolas", 50, 'bold'), anchor="center")
                        self.lineChooser = 7
                else:
                    self.lineChooser +=1
                    if self.lineChooser == 7:
                        self.canvas.create_line(wordCoordX + 150, wordCoordY - 150, wordCoordX + 175, wordCoordY - 80, width=2)
                        self.canvas.create_line(wordCoordX + 150, wordCoordY - 150, wordCoordX + 125, wordCoordY - 80, width=2)
                        self.canvas.create_text(wordCoordX+100,wordCoordY+100,text="YOU LOSE!", font= ("Consolas", 50, 'bold'),anchor="center")
                        self.canvas.itemconfig(self.displayedWord, text = word)
                    else:
                        if self.lineChooser == 1:
                            self.canvas.create_line(wordCoordX,wordCoordY-300,wordCoordX,wordCoordY-50,width=8)
                        elif self.lineChooser == 2:
                            self.canvas.create_line(wordCoordX,wordCoordY-300,wordCoordX+150,wordCoordY-300,width=8)
                        elif self.lineChooser == 3:
                            self.canvas.create_line(wordCoordX + 150, wordCoordY - 300, wordCoordX + 150, wordCoordY - 270, width = 4)
                        elif self.lineChooser == 4:
                            self.canvas.create_oval(wordCoordX +125, wordCoordY - 270, wordCoordX +175, wordCoordY -220, width=2)
                        elif self.lineChooser == 5:
                            self.canvas.create_line(wordCoordX + 150, wordCoordY -220, wordCoordX + 180, wordCoordY - 180, width=2)
                            self.canvas.create_line(wordCoordX + 150, wordCoordY -220, wordCoordX + 120, wordCoordY - 180, width=2)
                        else:
                            self.canvas.create_line(wordCoordX + 150, wordCoordY -220, wordCoordX +150, wordCoordY - 150, width=2)


                letter_id = self.lettersIDList.get(letter)
                x, y = self.canvas.coords(letter_id)
                line = self.canvas.create_line(x-10, y-10, x + 20, y + 20, width=4)
                self.listOfLines.append(line)

    def startGame(self):
        for widget in self.canvas.winfo_children():
            widget.pack_forget()
        fileName = str(self.topics[topic.get()]) + ".txt"
        file = open(fileName, 'r')
        lines = file.readlines()
        index=random.randrange(0,len(lines))
        word = lines[index].strip().upper()
        self.window.bind("<Key>", lambda event: self.InWord(event, word))
        self.hiddenWord = ''.join(["_"  if letter!=" " else " " for letter in word])
        file.close()
        screenWidth = self.canvas.winfo_screenwidth()
        self.displayedWord = self.canvas.create_text(screenWidth/2, self.canvas.winfo_screenheight()/2, text=self.hiddenWord,font=("Consolas",30,'bold'), anchor="center")
        self.pressedList = []
        englishLetters = [chr(code) for code in range(ord('A'), ord('Z') + 1)]
        self.lettersIDList = {}
        self.lineChooser = 0
        yCoord = 200
        startX = (screenWidth/2-532)
        for char in range(len(englishLetters)):
            if char!=0 and char%11==0:
                yCoord +=40
            char_id = self.canvas.create_text(startX+100*(char%11), self.canvas.winfo_screenheight()/2+yCoord, text=englishLetters[char], font=("Consolas",30,'bold'))
            self.lettersIDList[englishLetters[char].upper()] = char_id
        Button(self.canvas, text="Back to menu", font = ("Ink Free", 35, 'bold'), command=self.openMenu , bg="beige", activebackground="beige", bd = 3, relief="solid").pack(side = "right", padx=(0,50), pady=(0,400))
    def updateWord(self, letter, word):
        newWord = ''
        for index in range(len(word)):
            if word[index].isspace():
                newWord+=" "
            elif word[index] == letter:
                newWord+=letter
            else:
                newWord+=self.hiddenWord[index]
        return newWord
def main():
     game = Game()
     game.openMenu()
main()
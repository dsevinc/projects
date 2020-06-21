import random as r
from tkinter import Tk, Label, Button, Entry, IntVar, StringVar, END, W, E
import math
import logging
logging.basicConfig(level=logging.DEBUG, format='%(asctime)s - %(levelname)s - %(message)s')

class Game:
    def __init__(self,master):
        self.player1 = 0
        self.player2 = 0
        self.coinSide = ""

        self.master = master
        master.title("Game Counter")

        self.p1_score_text = IntVar()
        self.p1_score_text.set(self.player1)
        self.p1_label = Label(master, textvariable = self.p1_score_text)

        self.label_1 = Label(master, text="P1:")

        self.p2_score_text = IntVar()
        self.p2_score_text.set(self.player2)
        self.p2_label = Label(master, textvariable = self.p2_score_text)

        self.label_2 = Label(master, text="P2:")

        self.coin_text = StringVar()
        self.coin_text.set(self.coinSide)
        self.coin_label = Label(master, textvariable = self.coin_text)

        self.label_coin = Label(master, text="Coin Toss Result:")

        self.reset_button = Button(master, text="Reset", command=lambda: self.reset())
        self.add_p1_button = Button(master, text="P1+", command=lambda: self.addOne(1))
        self.add_p2_button = Button(master, text="P2+", command=lambda: self.addOne(2))

        self.subtract_p1_button = Button(master, text="P1-", command=lambda: self.subtractOne(1))
        self.subtract_p2_button = Button(master, text="P2-", command=lambda: self.subtractOne(2))

        self.coinToss_button = Button(master,text="Coin Toss", command=lambda: self.coinToss())

        #gui layout
        self.p1_label.grid(row=0, column=0, columnspan=5, sticky=E)
        self.p2_label.grid(row=0, column=5, columnspan=5, sticky=E)
        self.coin_label.grid(row=5, column=1, columnspan=5, sticky=E)

        self.reset_button.grid(row=1, column=0, columnspan=5, sticky=W+E)
        self.add_p1_button.grid(row=2, column=0, columnspan=2)
        self.add_p2_button.grid(row=2, column=2, columnspan=2)
        self.subtract_p1_button.grid(row=3, column=0, columnspan=2)
        self.subtract_p2_button.grid(row=3, column=2, columnspan=2)
        self.coinToss_button.grid(row=1, column=5, columnspan=2)

    def coinToss(self):
        result = r.randint(0,1)
        if (result == 0):
            self.coinSide = "HEADS"
        else:
            self.coinSide = "TAILS"

        self.update_display()

    def addOne(self,player):
        if(player == 1):
            self.player1 += 1
        else:
            self.player2 += 1

        self.update_display()

    def subtractOne(self,player):
        if(player == 1):
            self.player1 -= 1
        else:
            self.player2 -=1

        self.update_display()
    
    def reset(self):
        self.player1 = 0
        self.player2 = 0

        self.update_display()

    def update_display(self):
        self.p1_score_text.set(self.player1)
        self.p2_score_text.set(self.player2)
        self.coin_text.set(self.coinSide)


root = Tk()
my_gui = Game(root)
root.mainloop()

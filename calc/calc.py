from tkinter import Tk, Label, Button, Entry, DoubleVar, END, W, E
import math
import logging
logging.basicConfig(level=logging.DEBUG, format='%(asctime)s - %(levelname)s - %(message)s')

class Calculator:

    def __init__(self,master):
        self.master = master
        master.title("Calculator")

        self.total = 0.0
        self.input = 0.0

        self.total_label_text = DoubleVar()
        self.total_label_text.set(self.total)
        self.total_label = Label(master, textvariable = self.total_label_text)

        self.label = Label(master, text="Total:")

        vcmd = master.register(self.validate) #wrap the command
        self.entry = Entry(master, validate = "key", validatecommand=(vcmd, '%P'))

        self.reset_button = Button(master, text="Reset", command=lambda: self.reset())
        self.add_button = Button(master, text="+", command=lambda: self.add(self.input))
        self.subtract_button = Button(master, text="-", command=lambda: self.subtract(self.input))
        self.multiply_button = Button(master, text="x", command=lambda: self.multiply(self.input))
        self.divide_button = Button(master, text="÷", command=lambda: self.divide(self.input))
        self.sin_button = Button(master, text="sin", command=lambda: self.sinButton(self.input))
        self.cos_button = Button(master, text="cos", command=lambda: self.cosButton(self.input))
        self.equals_button = Button(master, text="=", command=lambda: self.equals(self))
        
        self.one_button = Button(master, text="1", command=lambda: self.validate(1))
        self.two_button = Button(master, text="2", command=lambda: self.validate(self,2))
        self.three_button = Button(master, text="3", command=lambda: self.validate(self,3))
        self.four_button = Button(master, text="4", command=lambda: self.validate(self,4))
        self.five_button = Button(master, text="5", command=lambda: self.validate(self,5))
        self.six_button = Button(master, text="6", command=lambda: self.validate(self,6))
        self.seven_button = Button(master, text="7", command=lambda: self.validate(self,7))
        self.eight_button = Button(master, text="8", command=lambda: self.validate(self,8))
        self.nine_button = Button(master, text="9", command=lambda: self.validate(self,9))
        self.zero_button = Button(master, text="0", command=lambda: self.validate(self,0))
        self.dot_button = Button(master, text=".", command=lambda: self.validate(self,'.'))

        #gui layout
        self.label.grid(row=0, column=0, sticky=W)
        self.total_label.grid(row=0, column=1, columnspan=5, sticky=E)

        self.entry.grid(row=1, column=0, columnspan=5, sticky=W+E)
        self.reset_button.grid(row=2, column=2, sticky=W+E)
        self.add_button.grid(row=2, column=0)
        self.subtract_button.grid(row=2, column=1)
        self.multiply_button.grid(row=3, column=0)
        self.divide_button.grid(row=3, column=1)
        self.sin_button.grid(row=3,column=2)
        self.cos_button.grid(row=3,column=3)
        self.nine_button.grid(row=5, column=0)
        self.eight_button.grid(row=5, column=1)
        self.seven_button.grid(row=5, column=2)
        self.six_button.grid(row=6, column=0)
        self.five_button.grid(row=6,column=1)
        self.four_button.grid(row=6, column=2)
        self.three_button.grid(row=7, column=0)
        self.two_button.grid(row=7, column=1)
        self.one_button.grid(row=7, column=2)
        self.dot_button.grid(row=7, column=3)
        self.equals_button.grid(row=7, column=4, columnspan=2, sticky=W+E)

    def update(self):
        self.total_label_text.set(self.total)
        self.entry.delete(0, END)

    def reset(self):
        self.total = 0
        self.update()

    def add(self,input):
        self.total += input
        self.update()

    def subtract(self,input):
        self.total -= input
        self.update()

    def multiply(self,input):
        self.total = self.total * input
        self.update()

    def divide(self,input):
        self.total = self.total / input
        self.update()

    def sinButton(self,input):
        self.total = math.sin(input)
        self.update()

    def cosButton(self,input):
        self.total = math.sin(input)
        self.update()

    def equals(self,method):
        self.total = method(self,)
        self.update()

    def validate(self, new_text):
        logging.debug(new_text)
        if not new_text: # the field is being cleared
            self.input = 0.0
            return True

        try:
            self.input = float(new_text)
            return True
        except ValueError:
            return False

root = Tk()
my_gui = Calculator(root)
root.mainloop()

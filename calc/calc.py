from tkinter import Tk, Label, Button, Entry, DoubleVar, END, W, E
import math

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
        self.total = math.sin(self.input)
        self.update() 

    def validate(self, new_text):
        print(new_text)
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

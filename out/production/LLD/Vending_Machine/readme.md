Functional Requirements

Select item
Insert money 
Dispense money
Return change 
handle out-of-stock

non-functional req 

extensible for payment methods 
maintainable 
thread-safe 

core entities

VendingMachine
Item
Slot
Inventory
Payment
State

design patterns

state pattern 
states--
idlestate
hasmoneystate
dispensingstate
outofstockstate

strategy pattern(for payment)
cash
card 
upi

factory method (for payment creation)

singleton pattern( for vendingmachine)


####Design Refactoring
######Grayson Wise -- Team 7

For Cell Society, I have been in charge of Data input and parsing. Because of the need to be able to read in many different types of XML configurations, I ended up creating a separate class for each simulations' data. This seemed like the easiest way to do it, but I soon found that I was writing a lot of the same code, and it would be easier to manage if I implemented an inheritance hierarchy that has one superclass "Data" and a bunch of subclasses implementing it in order to benefit from shared methods. 

I think that a Data method with corresponding subclasses is the right way to go about this because it makes implementing new types of XML files much easier. Every file has the same information that is absolutely necessary, but then they can implement their own specific information, while still using most of the Data class's methods. 

The commit with refactored code:
[Data input refactored to remove duplicated code.](https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team07/commit/8da4a8637bbc57d26b109354d62460133e01b396)
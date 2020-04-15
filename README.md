# RedBlackTree

####Red Black Tree (also known as P.L.A.N.T. (Parallel Log(n) Algorithm Nodes Tree))

This is an implementation of a Red Black Tree in Java.

My main focus of this project was to understand the Red Black Tree data structure and how it managed to achieve
efficient O(log n) time for search, insertion, and deletion.


I did this project because I enjoy teaching myself concepts before I would normally learn them in my classes, and 
I thought that researching and learning about the Red Black Tree would be a great way to start learning about more-advanced
algorithms and data structures.
I know that algorithms and data structures are essential parts of learning computer science so I decided to challenge myself
with a this self-balancing binary search tree. This was very difficult for me to figure out, but well-worth the effort.

## THE RED BLACK TREE: LEARNING ABOUT IT
I had to read chapters of textbooks (Algorithms, for example), watch countless YouTube videos, read articles for hours,
and read as many Reddit posts about binary search trees as I could, until I understood the red black tree.
It was briefly taught to me in Computer Science class but I didn't understand it then. 
For days I would take time during my regular daily tasks to listen to podcasts about this special structure. 
My favorite podcast was made by two female developers on codenewbie.org who discussed the rules of the structure and 
the benefits of using it, such as for its efficiency. 
I love organizing my thoughts, ideas, and visualizations about what I am learning on paper. For this project, I created
spreadsheets that can help someone (who might not know much about red black trees) understand it clearly.
One of my favorite things about Computer Science is the fact that so much information about it and learning tools to understand
it are online. There are so many free courses, book PDFs, articles, videos, practice sites, and forums that can provide someone
with the tools they need to further their skills in this field. I didn't want to just do a project where I look-up
how to do it as I go, I wanted to learn about it completely and take my time with understanding it before I solved it
for myself. 

![Spreadsheet 1](https://user-images.githubusercontent.com/57572818/79356056-7a839400-7f0c-11ea-94bc-68228f6101bd.jpg)
![Spreadsheet 2](https://user-images.githubusercontent.com/57572818/79356019-6f306880-7f0c-11ea-82a8-caf65106796c.jpg)

##THE RED BLACK TREE: WHAT IS IT?
Basically, a red black tree is a binary search tree that has rules to achieve logarithmic runtime, 
so that nodes have its children and also a color (red or black). 
The colors themselves don't matter. The root node must always be black, the nodes must always be red or black,
red nodes can't appear consecutively (red node must always have a black node parent, and vice versa), each branch
(from beginning root node to the ending leaf node) must have the same number of black nodes, and every leaf (the end node,
also classified as the nodes that are 'null') must be black. Red Black trees are really cool because they change the node colors,
rotate, and switch nodes around in-order to follow the 'rules' that I listed above. What is the benefit of doing this, though?


##THE RED BLACK TREE: WHERE ARE THEY USED?
Red Black Trees are extremely efficient and fast, and although they're difficult to understand, they are very useful for a 
variety of purposes. They are very useful to replace more simple types of trees, for the benefit of fast searches.
In the C++ Standard Template Library, red black trees are used for sets and maps. Red black trees
are often used in system symbol tables, like the Java TreeMap and TreeSet, or even the
Linux 'completely fair scheduler'.
They can also be used for databases, searching for words (such as in a dictionary), etc...


### LATER...
There are so many ways I can go about expanding this project in the future. My current idea is to develop a GUI for it, 
and possibly make a website online to show the GUI and also share information about the tree so that people can
understand it faster and better. It is a very confusing topic but one that is important to learn about because
of its various possible uses.

 



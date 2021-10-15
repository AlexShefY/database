#decription of the project

>We store the database in the form of a Cartesian tree.
>Cartesian tree vertices are stored as strings "used Hash priority key value left right selfit":
>>used - responsible for the presence of an element in the database
> 
>>Hash - key hash
> 
>>priority - priority node in Cartesian tree
> 
>>key - key of data
> 
>>value - value of data
> 
>>left -a pointer to the text character where the description of the left son of the node begins
>
>> right - a pointer to the text character where the description of the right son of the node begins
> 
>>selfit - a pointer to the text character where the description of the current node begins
>
>Operations "remove", "add", "extract", "in" are implemented through Cartesian tree operations "split"/"merge".
>We get quick access to arbitrary symbols by using RandomAccessFile, if we want to know information
> about any node. Also we can quickly change information for nodes.(functions for working with the file are in WorkWithFile.kt).
> 
>For operations "RemoveAll", "RemoveIf" we read all database and rebuild it with "build".
>For operations "filterOut", "out" we also read all database.


public class TrainLine {

    /** The name of the trainline */
    private String name;
    /** Points to the first station in the trainline */
    private TrainStation head;
    /** Points to the last station in the trainline */
    private TrainStation tail;
    /** Keeps a running tally of train stations in the trainline */
    private int numberOfStations;

    /** Full constructor */
    public TrainLine(String name, TrainStation head) {
        this.name = name;
        this.head = head;
        this.numberOfStations = 0;
        if (this.head != null) {
            // If head is not null, there is one station in the line
            this.numberOfStations = 1;
        }
        // At initialization head and tail point to the same station even if null
        this.tail = null;
    } // full constructor

    /** Basic constructor */
    public TrainLine(String name) {
        this(name, null);
    } // basic constructor

    /**
     * Creates a new station with the given name and adds it to the end of the line.
     */
    public void add(String name) {
        // Create the new station to add
        TrainStation newStation = new TrainStation(name);
        // Determine where to place the new station
        if (this.head == null) {
            // Trainline is empty, make new station the head of the line
            this.head = newStation;
        } else {
            // When there is a head station already, add the new station after the last
            // station in the line.
            this.tail.setNext(newStation);
        }
        // The new station becomes the tail station of the line
        this.tail = newStation;
        // Update station count
        this.numberOfStations++;
    } // method add

    /** Returns the number of stations in the line >= 0 */
    public int getNumberOfStations() {
        return numberOfStations;
    } // method getNumberOfStations

    public TrainStation remove(int position) {
        TrainStation removedStation = null;
        if (position >= 1 && position <= this.numberOfStations) {
            // Commence safe operations
            if (position == 1) {
                // Remove head
                removedStation = this.head;
                this.head = this.head.getNext();
            } else {
                // Find the station prior to the one to be removed
                TrainStation cursor = this.head;
                for (int i = 1; i < position-1; i++) {
                    cursor = cursor.getNext();
                }
                // cursor should be at the prior station
                if (cursor.getNext() == this.tail) {
                    this.tail = cursor;
                }
                removedStation = cursor.getNext();
                cursor.setNext(cursor.getNext().getNext());
            }
            this.numberOfStations--;
            removedStation.setNext(null);
        }
        return removedStation;
    }
    
public void insert(String name, int position) {
    // Check if the position is valid
    if (position < 1 || position > this.numberOfStations + 1) {
        //position is not possible, do nothing
        return;
    }
    // Create a new station with the given name
    TrainStation newStation = new TrainStation(name);
    if (position == 1) {
        // view if posiition is 1 continue
        newStation.setNext(this.head);
        this.head = newStation;
        //  set the tail to the new station if empty 
        if (this.tail == null) {
            this.tail = newStation;
        }
    } else {
        // find the inserted one
        TrainStation cursor = this.head;
        for (int i = 1; i < position - 1; i++) {
            cursor = cursor.getNext();
        }
        // places the new station after the cursor
        newStation.setNext(cursor.getNext());
        cursor.setNext(newStation);
        // if station is the new tail, fix it
        if (newStation.getNext() == null) {
            this.tail = newStation;
        }
    }
    // changes number of station
    this.numberOfStations++;
}

public String toString() {
    // builds the string representation of the train line
    StringBuilder sb = new StringBuilder();
    //  current station to the head of the line
    TrainStation current = head;
    int lineLength = 0;
    //true (right) or false (left)
    boolean direction = true; 
        int indentLevel = 0;
    while (current != null) {
        // get name
        String stationName = current.getName();
        // Get the length
        int stationLength = stationName.length();
        // makes sure it doesnt pass 80
        if (lineLength + stationLength + 4 > 80) {
            // If so,  newline character 
            sb.append("\n");
            lineLength = 0;
            // Change the direction when reaching 80 characters
            direction = !direction; 
        }

        // changes arrow depending
        if (direction) {
            if (lineLength == 0) {
                sb.append(stationName);
            } else {
                // arrow symbol
                sb.append(" --> ").append(stationName);
            }
        } else {
            if (lineLength == 0) {
                sb.append(stationName);
            } else {
                // Otherwise, append the name and the arrow symbol
                sb.append(stationName).append(" <--");
            }
        }

        // fixes line length
        lineLength += stationLength + (lineLength == 0 ? 0 : 4);

        // Move to the next station
        current = current.getNext();

        // If there's a next station,change arrow 
        if (current != null) {
            if (direction) {
                sb.append(" -->");
            } else {
                sb.append(" <--");
            }
        } else {
            //newline character
            sb.append("\n");
        }
    }

    // return to string 
    return sb.toString();
}

    public static void main(String[] args) {
        // A few station names
        String[] stationNames = { "Howard", "Jarvis", "Morse",
                "Loyola", "Granville", "Thorndale" };
        // A populated trainline
        TrainLine redLineSB = new TrainLine("Red Line SB");
        for (String station : stationNames) {
            redLineSB.add(station);
        }
        // An empty trainline
        prep_TrainLine brownLineSB = new prep_TrainLine("Brown Line SB");
        // A random station name
        String randomName = "Oak Park";
    } // method main
} // class TrainLine

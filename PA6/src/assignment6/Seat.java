/** Seat *****************************************************
 * Represents a seat at the Bates Recital Hall
 * 
 * Section : F 2:00 - 3:30pm
 * UT EID: cdr2678 ,rpm953
 * @author Cooper Raterink, Ronald Macmaster
 * @version 1.01 4/11/2016
 ************************************************************/

package assignment6;

/**
 * Represents a Seat at the Bates Recital Hall.
 * Can be compared to other Seats to determine which is the "better" seat
 */
public class Seat implements Comparable {

	/**
	 * Enum classifying the section of the theater
	 */
	public enum Section { Left, Middle, Right }
	
	/**
	 * Represents which section this Seat is in
	 */
	protected Section section;
	
	/**
	 * The row this Seat is in
	 */
	protected int row;
	
	/**
	 * This Seat's number
	 */
	protected int number;
	
	/**
	 * Regex that only matches a valid row name.
	 */
	protected final String ValidRowNameRegex = "AA|[A-Z]";
	
	/**
	 * Constructs this Seat with a certain section, row and number.
	 * @param section Section of the theater
	 * @param row Theater row
	 * @param number Seat number
	 */
	public Seat(Section section, int row, int number){
		this.section = section;
		this.row = row;
		this.number = number;
	}
	
	/**
	 * Converts a row number (1-27) to its row name:
	 *  (1 = "A", 2 = "B", ... , 27 = "AA")
	 * @param row Row number
	 * @return Row name
	 */
	public String rowToRowName(int row) {
		if(row > 27 || row < 1)
			return null;
		else
			return (row <= 26) ? String.valueOf('A' + row - 1) : "AA";
	}
	
	/**
	 * Converts a row's name ("A" thru "AA") to its corresponding row number (1-27).
	 * @param rowName Row's name
	 * @return If valid, the row's number. Otherwise, -1.
	 */
	public int rowNameToRow(String rowName) {
		//check if row name is invalid
		if(rowName == null || !rowName.matches(ValidRowNameRegex)) {
			return -1;
		}
		else if (rowName.length() == 1) {
			return (int) (rowName.charAt(0) - 'A' + 1);
		}
		else
			return 27;
	}
	
	@Override
	/**
	 * Comparable by the "value" of the seat:
	 * Front-middle seats are best, then front-sides, then the next-row-middle, etc
	 * @param otherSeatObj Seat to compare this seat to
	 * @return -1 if this seat is the "better" Seat,
	 * 			1 if the other seat is "better",
	 * 			0 if they are equal
	 */
	public int compareTo(Object otherSeatObj) {
		Seat otherSeat = (Seat) otherSeatObj; 
		
		//Compare rows first
		if(row != otherSeat.row)
			return (row < otherSeat.row) ? -1 : 1;
		
		//If in the same row, compare sections
		if (section != otherSeat.section) {
			if(section == Section.Middle)
				return 1;
			else if(otherSeat.section == Section.Middle) {
				return -1;
			}
		}
		
		//If seats are in same row and similarly-valued sections, 
		//they are of equal value
		return 0;
	}

}



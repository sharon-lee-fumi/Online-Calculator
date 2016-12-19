package business;

/**
 *
 * @author Xiaowen Li
 */
public class Operation 
{
    private double n1; 
    private double n2; 
    private String operator; 
    
    // constructor 
    // constructor without arguments (default constructor
    public Operation(){}
    
    // construvtor with arguments 
    public Operation(double n1, double n2, String operator)
    {
        this.n1 = n1; 
        this.n2 = n2; 
        this.operator = operator;
    }
    
    public double calculate()
    {
        double result = 0;
        if(operator.equals("+"))
        {
            result = n1+n2;
        }
        else if(operator.equals("-"))
        {
            result = n1-n2;
        }
        else if(operator.equals("/"))
        {
            result = n1/n2;
        }else
        {
            result = n1*n2;
        }
        
        return result;
        
    }

    public double getN1()
    {
        return n1;
    }

    public void setN1(double n1)
    {
        this.n1 = n1;
    }

    public double getN2()
    {
        return n2;
    }

    public void setN2(double n2)
    {
        this.n2 = n2;
    }

    public String getOperator()
    {
        return operator;
    }

    public void setOperator(String operator)
    {
        this.operator = operator;
    }

    public String toString()
    {
        return "number 1: " + n1 + "\n" +
               "number 2: " + n2 + "\n" + 
               "operator: " + operator + "\n";
    }
}

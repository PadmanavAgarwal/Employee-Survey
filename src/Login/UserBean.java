package Login;



public class UserBean 
{ private String username;
  private String password; 
  private String cart;
  private boolean attempted;
  private int position;
  private int rating;
 // private String firstName;
 // private String lastName; 
  private boolean valid;
 
  public String getCart() 
  { return cart; 
  }
  public void setCart(String newCart) 
  { cart = newCart; } 
  /*
  public String getLastName() 
  { return lastName; 
  }
  public void setLastName(String newLastName) 
  { lastName = newLastName;
  } */
  public String getPassword() 
  { return password;
  }
  public void setPassword(String newPassword)
  { password = newPassword;
  }
  public String getUsername() 
  { return username;
  } 
  public void setUserName(String newUsername) 
  { username = newUsername; 
  }
  public int getPosition() 
  { return position;
  } 
  public void setPosition(int newPos) 
  { position= newPos; 
  }
  public int getRating() 
  { return rating;
  } 
  public void setUserName(int newRating) 
  { rating = newRating; 
  }
  public boolean isValid()
  { return valid; 
  }
  public void setValid(boolean newValid) 
  { valid = newValid;
  }
  public boolean hasAttempted()
  { return attempted;
  }
  public void setAttempted(boolean newAttempt)
  { attempted=newAttempt;
  }
}

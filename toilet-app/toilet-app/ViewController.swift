//
//  ViewController.swift
//  toilet-app
//
//  Created by Mauricio Salatino on 10/03/2016.
//  Copyright © 2016 ToiletService. All rights reserved.
//

import UIKit


class ViewController: UIViewController, UITableViewDelegate, UITextFieldDelegate {

  
    @IBOutlet weak var homesTableView: UITableView!
    
    var homes: NSMutableArray = NSMutableArray();

    
    override func viewDidLoad() {
        super.viewDidLoad()
    
//        self.textField.delegate = self
        
        let url = NSURL(string: "http://localhost:8083/api/homes/")!
        
        NSURLSession.sharedSession().dataTaskWithURL(url) { (data, response, error) -> Void in
            if let urlContent = data {
                
                do{
                    
                    
                    self.homes = try NSJSONSerialization.JSONObjectWithData(urlContent, options: NSJSONReadingOptions.MutableContainers) as! NSMutableArray
                    
                    print(self.homes)
                    print(self.homes.count)
                    
                    dispatch_async(dispatch_get_main_queue(), { () -> Void in
                        
                        self.homesTableView.reloadData()
                        
                        
                    })

                    
                    
                    
                } catch {
                    print("Error parsing JSON")
                }
                
                
            }
            
            }.resume()
        

        // Do any additional setup after loading the view, typically from a nib.
    }

  
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int{
        return homes.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell{
        let cell = UITableViewCell(style: .Default, reuseIdentifier: "Cell")
        if let homeName = homes[indexPath.row]["name"]{
            cell.textLabel?.text = String(homeName!)
        } else{
            cell.textLabel?.text = String("No Name")
        }
        return cell
    }
    
    func tableView(tableView: UITableView, canEditRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        return true
    }
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath){
        print("Row Selected: "+String(homes[indexPath.row]["id"]))
        performSegueWithIdentifier("toHomeDetails", sender:tableView.cellForRowAtIndexPath(indexPath));
        
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject!) {
        print("tapping in cell" + String(sender))
        if let cell = sender as? UITableViewCell {
            let i = homesTableView.indexPathForCell(cell)!.row
            print("data here -> "+String(i))
            if segue.identifier == "toHomeDetails" {
                let vc = segue.destinationViewController as! HomeDetailsViewController
                print("data here2 -> "+String(homes[i]))
                vc.data = homes[i] as! NSDictionary
            }
        }
    }
    
    func tableView(tableView: UITableView, commitEditingStyle editingStyle: UITableViewCellEditingStyle, forRowAtIndexPath indexPath: NSIndexPath) {

        if (editingStyle == UITableViewCellEditingStyle.Delete) {
            if let id = homes[indexPath.row]["id"]{
                if let finalId = id{
                    print("Deleting.... \(finalId)")
                    let url = NSURL(string: "http://localhost:8083/api/homes/\(finalId)")!
            
                    let request = NSMutableURLRequest(URL: url)
                    let session = NSURLSession.sharedSession()
            
            
                    request.HTTPMethod = "DELETE"
                    request.setValue("application/json; charset=utf-8", forHTTPHeaderField: "Content-Type")
            
            
                // Make the POST call and handle it in a completion handler
                    session.dataTaskWithRequest(request, completionHandler: { ( data: NSData?, response: NSURLResponse?, error: NSError?) -> Void in
                // Make sure we get an OK response
                    
                        
                        guard let realResponse = response as? NSHTTPURLResponse where
                            realResponse.statusCode == 204 else {
                                print("Not a 204 response")
                                return
                        }

                        dispatch_async(dispatch_get_main_queue(), { () -> Void in
                            self.homes.removeObjectAtIndex(indexPath.row)
                            
                            self.homesTableView.reloadData()
                        
                        
                        })
                    
              
                    
                    }).resume()
                }
            }else{
                print("Wrong ID");
            }
        }
    }
    
    
    /*
        * This two methods are for closing the keyboard when it bothers
    */
    
    override func touchesBegan(touches: Set<UITouch>, withEvent event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    func textFieldShouldReturn(textfield : UITextField ) -> Bool {
        textfield.resignFirstResponder()
        return true;
    }
    
    
    
    
    

}


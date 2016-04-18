//
//  AddHomeViewController.swift
//  toilet-service
//
//  Created by Mauricio Salatino on 23/03/2016.
//  Copyright © 2016 ToiletService. All rights reserved.
//

import UIKit

class AddHomeViewController: UIViewController {
    
    @IBOutlet weak var homeNameText: UITextField!
    
    @IBOutlet weak var saveButton: UIBarButtonItem!
    
    var newHome: Dictionary<String, AnyObject> = [:];
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    
    @IBAction func saveNewHome(sender: AnyObject) {
        
        let url = NSURL(string: serviceUrl)!
        
        let request = NSMutableURLRequest(URL: url)
        let session = NSURLSession.sharedSession()
        
        newHome = ["name": homeNameText.text!];
        
        request.HTTPMethod = "POST";
        request.setValue("application/json; charset=utf-8", forHTTPHeaderField: "Content-Type")
        
        do {
            request.HTTPBody = try NSJSONSerialization.dataWithJSONObject(newHome, options: NSJSONWritingOptions())
            
        } catch {
            print("bad things happened")
        }
        
        // Make the POST call and handle it in a completion handler
        session.dataTaskWithRequest(request, completionHandler: { ( data: NSData?, response: NSURLResponse?, error: NSError?) -> Void in
            // Make sure we get an OK response
            guard let realResponse = response as? NSHTTPURLResponse where
                realResponse.statusCode == 200 else {
                    print("Not a 200 response")
                    return
            }
            
            // Read the JSON
            if let homeId = NSString(data:data!, encoding: NSUTF8StringEncoding) as? String {
                // Print what we got from the call
                
                dispatch_async(dispatch_get_main_queue(), { () -> Void in
                    // do something if needed
                    print("setting id \(homeId)...")
                    self.newHome["id"] = homeId;
                    self.performSegueWithIdentifier("unwindToHomeList",sender: self)
                    
                })
                
                
            }
            
        }).resume()
    }
    // MARK: - Navigation
    
    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
        
        
        
        
    }
    
    
}

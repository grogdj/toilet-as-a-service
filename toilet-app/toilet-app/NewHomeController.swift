//
//  NewHomeController.swift
//  toilet-app
//
//  Created by Mauricio Salatino on 15/03/2016.
//  Copyright © 2016 ToiletService. All rights reserved.
//

import UIKit


class NewHomeViewController: UIViewController {
    
    
    @IBOutlet weak var homeNameText: UITextField!
    
    @IBAction func saveNewHome(sender: AnyObject) {
        let url = NSURL(string: "http://localhost:8083/api/homes/")!

        let request = NSMutableURLRequest(URL: url)
        let session = NSURLSession.sharedSession()
        let postParams = ["name": homeNameText.text!,"persons": [], "bathrooms": []] as Dictionary<String, AnyObject>
        
        request.HTTPMethod = "POST"
        request.setValue("application/json; charset=utf-8", forHTTPHeaderField: "Content-Type")
        
        do {
            request.HTTPBody = try NSJSONSerialization.dataWithJSONObject(postParams, options: NSJSONWritingOptions())
            print(postParams)
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
            if let postString = NSString(data:data!, encoding: NSUTF8StringEncoding) as? String {
                // Print what we got from the call
                print("POST: " + postString)
                dispatch_async(dispatch_get_main_queue(), { () -> Void in
                    
                    let secondViewController = (self.storyboard?.instantiateViewControllerWithIdentifier("main"))! as UIViewController
                    self.presentViewController(secondViewController, animated: true, completion: nil)
//                    self.navigationController?.pushViewController(secondViewController, animated: true)

                    
                    
                })

               // self.performSelectorOnMainThread("updatePostLabel:", withObject: postString, waitUntilDone: false)
                            }
            
        }).resume()
    }
    
    
    
    override func viewDidAppear(animated: Bool) {
        print("I'm in the NewHomeViewController")
    }
    

}
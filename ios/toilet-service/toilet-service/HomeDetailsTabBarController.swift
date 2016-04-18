//
//  HomeDetailsTabBarController.swift
//  toilet-service
//
//  Created by Mauricio Salatino on 22/03/2016.
//  Copyright © 2016 ToiletService. All rights reserved.
//

import UIKit

class HomeDetailsTabBarController: UITabBarController {

    var home: NSMutableDictionary = NSMutableDictionary();
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "Toilet fun"
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}

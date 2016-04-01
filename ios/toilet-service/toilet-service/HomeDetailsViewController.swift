//
//  HomeDetailsViewController.swift
//  toilet-service
//
//  Created by Mauricio Salatino on 22/03/2016.
//  Copyright © 2016 ToiletService. All rights reserved.
//

import UIKit

class HomeDetailsViewController: UIViewController {
    
    var home: NSDictionary = NSDictionary();
    
    @IBOutlet weak var nameLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let tbc = self.tabBarController  as! HomeDetailsTabBarController
        home = tbc.home
        nameLabel.text = home["name"] as? String
        
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

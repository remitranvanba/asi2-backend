import { Card, CardContent, Typography } from "@mui/material";

interface Props {
    id: string; 
    name: string; 
    participants: string[]; 
    selected: boolean;
    onClick:(...params: any[]) => void;
}

export default function Channel({id, name, onClick} : Props) {
    const click = () => {
        onClick(id);
    }

    return (
        <div className='channel-item' onClick={click} style={{ borderRadius: "1em", margin: "1em"}}>
            <Card className="pt-3 pb-0">
                <CardContent>
                    <Typography
                    gutterBottom
                    variant="h5"
                    component="div"
                    className="text-center"
                    >
                    {name}
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                    
                    </Typography>
                </CardContent>
            </Card>
        </div>
    )
}